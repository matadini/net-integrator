
// example call: groovy json-upper.groovy "{ \"name\":\"Janusz\", \"surname\":\"Nosacz\"}"

// imports
import groovy.json.JsonOutput
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

// return data
print JsonOutput.toJson(newPerson)
