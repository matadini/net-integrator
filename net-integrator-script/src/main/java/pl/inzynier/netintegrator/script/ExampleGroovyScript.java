package pl.inzynier.netintegrator.script;

public class ExampleGroovyScript {

    public static String createExampleGroovyScriptJsonToXml() {
        return "// imports\n" +
                "import groovy.json.JsonSlurper\n" +
                "\n" +
                "//define input data class\n" +
                "class Person {\n" +
                "    String name\n" +
                "    String surname\n" +
                "}\n" +
                "\n" +
                "// Read input data\n" +
                "def input = args[0]\n" +
                "\n" +
                "// convert to UDR\n" +
                "def slurper = new JsonSlurper()\n" +
                "def personMap = slurper.parseText(input)\n" +
                "def newPerson = new Person(personMap)\n" +
                "\n" +
                "// transform data\n" +
                "newPerson.name = newPerson.name.toUpperCase()\n" +
                "newPerson.surname = newPerson.name.toUpperCase()\n" +
                "\n" +
                "def node = new groovy.util.Node(null, \"person\")\n" +
                "node.appendNode(\"surname\",newPerson.surname)\n" +
                "node.appendNode(\"name\",newPerson.name)\n" +
                "\n" +
                "// return data\n" +
                "def serialize = groovy.xml.XmlUtil.serialize(node).replaceAll(\"\\\\s\",\"\")\n" +
                "print serialize\n" +
                "return serialize\n";
    }

    public static String createExampleGroovyScriptToUpperCase() {
        return "//imports\n" +
                "import groovy.json.JsonOutput\n" +
                "import groovy.json.JsonSlurper\n" +
                "//define input data class\n" +
                "class Person {\n" +
                "    String name\n" +
                "    String surname\n" +
                "}\n" +
                "// Read input data\n" +
                "def input = args[0]\n" +
                "// convert to UDR\n" +
                "def slurper = new JsonSlurper()\n" +
                "def personMap = slurper.parseText(input)\n" +
                "def newPerson = new Person(personMap)\n" +
                "// transform data\n" +
                "newPerson.name = newPerson.name.toUpperCase()\n" +
                "newPerson.surname = newPerson.surname.toUpperCase()\n" +
                "// return data\n" +
                "return JsonOutput.toJson(newPerson)\n";
    }


    public static String toUpperCaseOnly() {
        return "def input = args[0]\n" +
                "return input.toUpperCase()";
    }
}
