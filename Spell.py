import urllib.request as req


class Spell:
    def __init__(self, name):
        self.name = name
        self.classLinked = ""
        self.components = []
        self.level = None
        self.resistance = None
        self.url = ""
        self.description=""
        self.creatures = []
        self.url = req.pathname2url(name)
