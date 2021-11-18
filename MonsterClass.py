import urllib.request as req

class Monster:
    def __init__(self, url):
        self.name = url.split("=")[1]
        self.url = url.split("=")[0] + "=" + req.pathname2url(self.name)