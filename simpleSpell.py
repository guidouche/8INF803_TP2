

class Spell:
    def __init__(self, name):
        self.name = name
        self.creature = []

    def addCreature(self,creatureName):
        self.creature.append(creatureName)

    def CreatureList(self):
        for i in range(0,len(self.creature)):
            print (self.creature[i])
