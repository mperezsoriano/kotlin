/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.asJava.elements

import com.intellij.openapi.util.Key
import com.intellij.openapi.util.UserDataHolder
import com.intellij.psi.PsiMember
import com.intellij.psi.PsiTypeElement
import com.intellij.psi.impl.compiled.ClsRepositoryPsiElement
import com.intellij.psi.impl.compiled.ClsTypeElementImpl
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

@JvmField
val DEFERRED_RETURN_TYPE = Key.create<Function0<String>>("DEFERRED_RETURN_TYPE")


internal fun KtLightMember<*>.computeChildTypeElement(
    clsDelegateTypeElement: PsiTypeElement?
): PsiTypeElement? {
    val delegateTypeElement = clsDelegateTypeElement as? ClsTypeElementImpl
    val canonicalText =
        clsDelegate.safeAs<ClsRepositoryPsiElement<*>>()?.stub?.safeAs<UserDataHolder>()
            ?.getUserData(DEFERRED_RETURN_TYPE)
            ?.invoke()
                ?: delegateTypeElement?.canonicalText
                ?: return null

    return ClsTypeElementImpl(this, canonicalText, /*ClsTypeElementImpl.VARIANCE_NONE */ 0.toChar())
}
