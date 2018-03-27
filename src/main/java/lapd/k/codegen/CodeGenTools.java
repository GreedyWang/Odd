package lapd.k.codegen;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.github.javaparser.ast.Modifier.PRIVATE;


//import com.github.javaparser.ASTHelper;

public class CodeGenTools {

    private static String className = "VehicleStateInfo";

    private static String classPath;

    private static String jarPrefix;

    private static String jarAbsolutePath;

    static {
        classPath = CodeGenTools.class.getClassLoader().getResource("").getPath();
        classPath = !classPath.startsWith("/") ? classPath : classPath.substring(1);//去掉开始位置的/
        classPath = classPath.endsWith(File.separator) ? classPath : classPath + File.separator;
        jarPrefix = classPath.substring(0, classPath.lastIndexOf("classes")) + File.separator + "lib" + File.separator;
        //hard code for dependency
        //todo: hard code for dependency lib path
        jarAbsolutePath = "/Users/wangyongmin/.m2/repository/org/projectlombok/lombok/1.16.18/lombok-1.16.18.jar";
//                new StringBuilder().append(jarPrefix)
//                .append("hibernate-core-4.2.0.Final.jar;")
//                .append(jarPrefix).append("hibernate-jpa-2.0-api-1.0.1.Final.jar;")
//                .append(jarPrefix).append("validation-api-1.0.0.GA.jar;");
    }

    private static List<PropertyFactory.Cfg> loadCfg() {
        String file = CodeGenTools.class.getResource("/codegen.xml").getFile();
        return PropertyFactory.read(file);
    }

    /**
     * generate class
     * @param className
     */
    public static void gen(String className) {

        NodeList<MemberValuePair> pairs = new NodeList();
        // pairs.add(new MemberValuePair("name" , new AnnotationExpr()));
        NormalAnnotationExpr normalAnnotationExpr = new NormalAnnotationExpr(
                new Name("Table"),
                NodeList.nodeList
                        (
                                new MemberValuePair("name", new StringLiteralExpr("vehicle_status_info")),
                                new MemberValuePair("uniqueConstraints", new StringLiteralExpr("@UniqueConstraint(columnNames = { \"tbox_id\" })"))
                        )
        );

        MethodDeclaration method = new MethodDeclaration(EnumSet.of(Modifier.PUBLIC), "fillin", new VoidType(), NodeList.nodeList(new Parameter(new ArrayType(PrimitiveType.byteType()), "data")));

        ImportDeclaration importDeclaration = new ImportDeclaration("lombok.Data", false, false);

        CompilationUnit compilationUnit = new CompilationUnit();

        compilationUnit = compilationUnit.addImport(importDeclaration);

        ClassOrInterfaceDeclaration myClass = compilationUnit
                .addClass(className)
                .setPublic(true)
                .addAnnotation("Data")
                .addAnnotation("Entity")
                .addAnnotation(normalAnnotationExpr)
                // .addMember(importDeclaration)
                .addMember(method);


        List<PropertyFactory.Cfg> cfgList = loadCfg();

        for (PropertyFactory.Cfg cfg : cfgList) {

            myClass.addField(getType(cfg.type), cfg.name, PRIVATE);
        }


        String code = compilationUnit.toString();
        System.out.println(code);
        //todo: save to the classpath

    }

    private static Class<?> getType(String type) {
        switch (type) {
            case "int":
                return int.class;

            case "String":
                return String.class;

            case "float":
                return float.class;

            default:
                return String.class;
        }
    }

    public static void main(String args[]) {
        CodeGenTools.gen(className);
        try {
            compiler(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Object obj = Optional.of(loadClass(null)).get();

        try {
            Test.test(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Thread.currentThread().getClass().getClassLoader().loadClass();
    }


    public static Object loadClass(String path) {
        String javaPackageName = className.replace(".", File.separator) + ".class";
        String javaAbsolutePath = File.separator + classPath + javaPackageName;
        MyClassLoader classLoader = new MyClassLoader(javaAbsolutePath);
        try {
            Object obj = classLoader.findClass("codegen.VehicleStateInfo").newInstance();
            System.out.println(obj);
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 编译java类
     * 使用rt.jar中的javax.tools包提供的编译器
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @throws java.io.IOException
     */
    public static void compiler(String name) throws IOException {
        String javaPackageName = className.replace(".", File.separator) + ".java";
        String javaAbsolutePath = File.separator + classPath + javaPackageName;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, System.out, "-encoding",
                "UTF-8", "-classpath", jarAbsolutePath, javaAbsolutePath);
    }
}
