package org.example.representation;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Usage:generate_ast <output directory>");
            System.exit(64);
        }

        String outputDirectory = args[0];
    }


    private static void defineAst(
            String outputDir,
            String name,
            List<String> types
    ) throws IOException {
        String path = outputDir + "/"+name+".java";
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

        writer.println("package test;");
        writer.println();
        writer.println("import class");
        writer.println();
        writer.println("abstract class "+name+" {");

        defineVisitor(writer, name, types);
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, name, className, fields);
        }

        writer.println();
        writer.println("   abstract <R> R accept(Visitor<R> visitor);");
        writer.println("}");
        writer.close();
    }

    private static void defineVisitor(
            PrintWriter writer,
            String name,
            List<String> types
    ) {
        writer.println("interface Visitor<R> {");
        for (String type: types) {
            String typeName = type.split(":")[0].trim();
            writer.println("   R visit" + typeName + name +
                    "(" + typeName + " " + name.toLowerCase() + ");");
        }
        writer.println("  }");


    }

    private static void defineType(
            PrintWriter writer,
            String baseName,
            String className,
            String fieldsList
    ) {
        writer.println(" static class "+className+" extends "+ baseName +" {");

        writer.println("    "+className + "(" + fieldsList + ") {");

        String[] fields = fieldsList.split(", ");
        for (String field: fields) {
            String name = field.split(" ")[1];
            writer.println("      this." + name + " = " + name + ";");
        }

        writer.println("    }");


        writer.println();
        for (String field : fields) {
            writer.println("    final " + field + ";");
        }

        writer.println("  }");

        writer.println();
        writer.println("    @Override");
        writer.println("    <R> R accept(Visitor<R> visitor) {");
        writer.println("      return visitor.visit" +
                className + baseName + "(this);");
        writer.println("    }");
    }
}
