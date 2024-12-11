package com.example.codeclicker.game

data class ProgrammingLanguageList(
    val lines: List<String>,
    val errorLines: List<String>
)

val javaLanguage = ProgrammingLanguageList(
    lines = listOf(
        "public static void main;",
        "int[] nums = new int[5];",
        "if (nombre.contains(\"a\"));",
        "abstract class Usuario",
        "interface Drawable { void draw(); }",
        "String mensaje = \"Hola Mundo!\";",
        "public class Cliente extends Persona { }",
        "Map<String, Integer> mapa = new HashMap<>();",
        "try { throw new IOException(); }",
        "List<Integer> num = Arrays.asList(1,2,3);",
        "Runnable r = () -> System.out.println(\"Play\");",
        "System.out.printf(\"Resultado: %.2f\", res);",
        "int resultado = Math.max(a, b);",
        "Path ruta = Paths.get(\"archivo.txt\");"
    ),
    errorLines = listOf(
        "publif stat",
        "ic vod main"
    )
)

val kotlinLanguage = ProgrammingLanguageList(
    lines = listOf(
        "fun main() { println(\"Hola, Mundo!\") }",
        "val nums = IntArray(5) { it * 2 }",
        "if (nombre.contains(\"a\")) { }",
        "abstract class Usuario(val id: Int)",
        "interface Drawable { fun draw() }",
        "val mensaje = \"Hola Mundo!\"",
        "class Cliente : Persona()",
        "val mapa = mutableMapOf<String, Int>()",
        "try { throw IOException() } catch (e: IOException)",
        "val numeros = listOf(1, 2, 3)",
        "val runnable = Runnable { println(\"Play\") }",
        "println(\"Resultado: %.2f\".format(res))",
        "val resultado = maxOf(a, b)",
        "val ruta = Paths.get(\"archivo.txt\")"
    ),
    errorLines = listOf(
        "fun man()",
        "vl nums = [1, 2, 3]"
    )
)

val swiftLanguage = ProgrammingLanguageList(
    lines = listOf(
        "print(\"Hello\")",
        "let nums = [1, 2, 3]",
        "if nombre.contains(\"a\") { print(\"Tiene 'a'\") }",
        "class Usuario { var id: Int = 0 }",
        "protocol Drawable { func draw() }",
        "let mensaje = \"Hola\"",
        "class Cliente: Persona { }",
        "var mapa: [String: Int] = [:]",
        "let lambda = { print(\"Play\") }",
        "print(\"Resultado: \\(res)\")",
        "let resultado = max(a, b)",
        "let url = URL(fileURLWithPath: \"archivo.txt\")"
    ),
    errorLines = listOf(
        "let nums [1, 2, 3]",
        "fun main() { print(\"Hello\") }"
    )
)

val cLanguage = ProgrammingLanguageList(
    lines = listOf(
        "#include <stdio.h>",
        "int main() { printf(\"Hello World!\"); return 0; }",
        "int nums[5] = {1, 2, 3, 4, 5};",
        "if (strchr(nombre, 'a')) { printf(\"Tiene 'a'\"); }",
        "typedef struct Usuario { char nombre[50]; } Usuario;",
        "void draw() { printf(\"Drawing...\\n\"); }",
        "char mensaje[] = \"Hola Mundo!\";",
        "struct Cliente : Persona { };",
        "#include <stdlib.h>",
        "int* nums = malloc(3 * sizeof(int)); nums[0] = 1;",
        "void (*func)() = draw;",
        "printf(\"Resultado: %.2f\\n\", res);",
        "int resultado = (a > b) ? a : b;",
        "FILE* archivo = fopen(\"archivo.txt\", \"r\");"
    ),
    errorLines = listOf(
        "int min() {",
        "in nums[] = {1,2,3}"
    )
)

val pythonLanguage = ProgrammingLanguageList(
    lines = listOf(
        "print(\"Hello\")",
        "nums = [1, 2, 3]",
        "if 'a' in nombre: print(\"Tiene 'a'\")",
        "class Usuario: pass",
        "def draw(): print(\"Drawing...\")",
        "mensaje = \"Hola\"",
        "class Cliente(Persona): pass",
        "mapa = {'clave': 1}",
        "lambda_func = lambda: print(\"Play\")",
        "print(f\"Resultado: {res}\")",
        "resultado = max(a, b)",
        "with open(\"file.txt\", \"r\") as file: data = file.read()"
    ),
    errorLines = listOf(
        "prnt(\"Hello\")",
        "nums = [1, 2 3]"
    )
)

val javascriptLanguage = ProgrammingLanguageList(
    lines = listOf(
        "console.log(\"Hello\");",
        "const nums = [1, 2, 3];",
        "if (nombre.includes('a')) console.log(\"´Sí\");",
        "class Usuario { constructor(id) { this.id = id; } }",
        "function draw() { console.log(\"Drawing...\"); }",
        "const mensaje = \"Hola\";",
        "class Cliente extends Persona { }",
        "const mapa = new Map([['clave', 1]]);",
        "const lambda = () => console.log(\"Play\");",
        "console.log(`Resultado: \${res}`);",
        "const resultado = Math.max(a, b);",
        "const data = fs.readFileSync(\"file.txt\", \"utf8\");"
    ),
    errorLines = listOf(
        "console.log(\"Hello\"",
        "const nums = [1 2, 3];"
    )
)