package com.interneted.test_navigation.helper

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import kotlinx.coroutines.runBlocking
import java.util.*


/**
 * Creator: ED
 * Date: 2/2/21 3:25 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
@Navigator.Name("fixFragment")
class FixFragmentNavigator(
    private val mContext: Context,
    private val mManager: FragmentManager,
    private val mContainerId: Int
) :
    FragmentNavigator(mContext, mManager, mContainerId) {

    companion object {
        const val BUNDLE_STATE = "BUNDLE_STATE"
    }

    private val TAG = "FixFragmentNavigator"

    private val mBackStack: ArrayDeque<Int> = run {
        val field = FragmentNavigator::class.java.getDeclaredField("mBackStack")
        field.isAccessible = true
        field.get(this) as ArrayDeque<Int>
    }

    override fun popBackStack(): Boolean {

        if (mBackStack.isEmpty()) {
            return false
        }
        if (mManager.isStateSaved) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already" + " saved its state")

            return false
        }

        val needRemoveFragmentId = mBackStack.peekLast() ?: -1
        mManager.popBackStack(
            generateBackStackName(mBackStack.size, needRemoveFragmentId),
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        mBackStack.removeLast()

        val ft = mManager.beginTransaction()

        val needRemoveFragment = mManager.findFragmentByTag(needRemoveFragmentId.toString())
        if (needRemoveFragment != null) {
            ft.remove(needRemoveFragment)
        }

        val needShowFragmentId = mBackStack.peekLast() ?: -1

        val needShowFragment = mManager.findFragmentByTag(needShowFragmentId.toString())
        if (needShowFragment != null) {
            ft
                .show(needShowFragment)
                .setPrimaryNavigationFragment(needShowFragment)
                .setMaxLifecycle(needShowFragment, Lifecycle.State.RESUMED)
        }
        ft.commitNow()

        return true
    }

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        if (mManager.isStateSaved) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already" + " saved its state")
            return null
        }
        // 取得目標的 className
        var className = destination.className
        if (className[0] == '.') {
            className = mContext.packageName + className
        }
        // 啟動 Fragment 事務
        val ft = mManager.beginTransaction()

        // 設定自定義動畫
        var enterAnim = navOptions?.enterAnim ?: -1
        var exitAnim = navOptions?.exitAnim ?: -1
        var popEnterAnim = navOptions?.popEnterAnim ?: -1
        var popExitAnim = navOptions?.popExitAnim ?: -1
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            enterAnim = if (enterAnim != -1) enterAnim else 0
            exitAnim = if (exitAnim != -1) exitAnim else 0
            popEnterAnim = if (popEnterAnim != -1) popEnterAnim else 0
            popExitAnim = if (popExitAnim != -1) popExitAnim else 0
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
        }

        // 原先 FragmentNavigator 的實作方法
        // ft.replace(mContainerId, frag)


        /**
         * 1. 查詢當前顯示的 Fragment，不為空則將其 hide
         * 2. 根據 tag 查詢預添加的 Fragment，不為空則將其 show
         * 3. 上列若為空則通過 instantiateFragment 方法創建 Fragment 實例將其 add
         * */

        val fragment = mManager.primaryNavigationFragment //当前显示的fragment
        if (fragment != null) {
            ft.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
            ft.hide(fragment)
        }

        // 判斷是否有添加 Fragment，以此作為設定返回棧的依據
        var hasFragmentAdded = false


        var frag: Fragment?
        val tag = destination.id.toString()
        frag = mManager.findFragmentByTag(tag)

        if (frag == null) {
            // 添加 Fragment
            hasFragmentAdded = true
            frag = instantiateFragment(mContext, mManager, className, args)
            frag.arguments = args
            ft.add(mContainerId, frag, tag)
        } else {
            val savedState = args?.getParcelable<Fragment.SavedState>(BUNDLE_STATE)
            if (savedState != null) {
                // 刪除原先的 Fragment
                // 添加同一個 Fragment，且恢復狀態
                frag = instantiateFragment(mContext, mManager, className, args)
                frag.setInitialSavedState(savedState)
                frag.arguments = args
                ft.replace(mContainerId, frag, tag)
            } else {
                ft.show(frag)
            }
        }

        ft.setMaxLifecycle(frag, Lifecycle.State.RESUMED)
        ft.setPrimaryNavigationFragment(frag)

        @IdRes val destId = destination.id


        val initialNavigation = mBackStack.isEmpty()
        val isSingleTopReplacement = (navOptions != null && !initialNavigation
                && navOptions.shouldLaunchSingleTop()
                && mBackStack.peekLast() == destId)

        val isAdded: Boolean
        if (initialNavigation) {
            isAdded = true
        } else if (isSingleTopReplacement) {
            // Single Top means we only want one instance on the back stack
            if (mBackStack.size > 1) {
                // If the Fragment to be replaced is on the FragmentManager's
                // back stack, a simple replace() isn't enough so we
                // remove it from the back stack and put our replacement
                // on the back stack in its place
                mManager.popBackStack(
                    generateBackStackName(mBackStack.size, mBackStack.peekLast()),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                ft.addToBackStack(generateBackStackName(mBackStack.size, destId))
            }
            isAdded = false
        } else if (hasFragmentAdded) {
            ft.addToBackStack(generateBackStackName(mBackStack.size + 1, destId))
            isAdded = true
        } else {
            isAdded = false
        }
        if (navigatorExtras is Extras) {
            val extras = navigatorExtras as Extras?
            for ((key, value) in extras!!.sharedElements) {
                ft.addSharedElement(key, value)
            }
        }
        ft.setReorderingAllowed(true)
        ft.commit()
        // The commit succeeded, update our view of the world
        if (isAdded) {
            mBackStack.add(destId)
            return destination
        } else {
            return null
        }
    }


    /**
     * 在父类是 private的  直接定义一个方法即可
     */
    private fun generateBackStackName(backIndex: Int, destid: Int): String {
        return "$backIndex - $destid"
    }


}


/**
 * 恢復狀態而設計的
 * */
fun NavController.navigateWithRestoreState(
    @IdRes navDirectionsId: Int,
    savedState: Fragment.SavedState
) {

    navigate(
        navDirectionsId,
        bundleOf(FixFragmentNavigator.BUNDLE_STATE to savedState),
        NavOptions.Builder()
            .setPopUpTo(navDirectionsId, false)
            .build()
    )

}