// imports
import groovy.json.JsonSlurper

//define input data class
class Person {
    String name
    String surname
}

// Read input data
def input = args[0]

// convert to UDR
def slurper = new JsonSlurper()
def personMap = slurper.parseText(input)
def newPerson = new Person(personMap)

// transform data
newPerson.name = newPerson.name.toUpperCase()
newPerson.surname = newPerson.name.toUpperCase()

def node = new groovy.util.Node(null, "person")
node.appendNode("surname",newPerson.surname)
node.appendNode("name",newPerson.name)

// return data
def serialize = groovy.xml.XmlUtil.serialize(node).replaceAll("\\s","")
print serialize
return serialize
