// IGNORE_BACKEND: JS_IR
// EXPECTED_REACHABLE_NODES: 1110
// SOURCE_MAP_EMBED_SOURCES: ALWAYS
// FILE: a.kt
fun foo() = "O"

// FILE: b.kt
// RECOMPILE
fun bar() = "K"

// FILE: main.kt
// RECOMPILE
fun box() = foo() + bar()