import sys
import json

# example call: py test.py '{ "name":"Janusz", "surname":"Nosacz"}'

# define input data structure
class Person:
    def __init__(self, name, surname):
        self.name = name
        self.surname = surname

# define ouput data structure


# read input args
arg = sys.argv[1]
loads = json.loads(arg)
object = Person(**loads)

# transform data


# return data
print("Hello new: " + object.name)