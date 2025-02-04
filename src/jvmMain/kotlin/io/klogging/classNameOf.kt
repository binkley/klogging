/*

   Copyright 2021 Michael Strasser.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package io.klogging

import kotlin.reflect.KClass

/**
 * Notes:
 * 1. The companion class can have a different name than "$Companion"
 * 2. Kotlin reflection is only supported for JVM, not for JS
 */
public actual fun classNameOf(ownerClass: KClass<*>): String? =
    if (ownerClass.isCompanion) ownerClass.java.enclosingClass.name else ownerClass.java.name
