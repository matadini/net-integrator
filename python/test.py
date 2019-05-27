import sys
import json

# example call: py test.py '{ "name":"Janusz", "surname":"Nosacz"}'

# define input data structure
class Person:
    def __init__(self, name, surname):
        self.name = name
        self.surname = surname

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, 
            sort_keys=True, indent=4)
# define ouput data structure


# read input args
arg = sys.argv[1]
loads = json.loads(arg)
object = Person(**loads)

# transform data
object.name = object.name.upper()
object.surname = object.surname.upper()

# return data
print(object.toJSON())