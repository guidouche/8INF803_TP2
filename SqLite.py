
import sqlite3

db_name = 'exo2.db'

create_spell_query = '''CREATE TABLE spell (
                            name TEXT PRIMARY KEY,
                            level INTEGER,
                            components TEXT,
                            spell_resistance  INTEGER,
                            class_linked TEXT,
                            url TEXT,
                            description TEXT,
                             creatures TEXT);'''

create_creature_query='''CREATE TABLE creature(name TEXT PRIMARY KEY,
                                    url TEXT);'''

insert_spell_query = ''' INSERT OR REPLACE INTO spell(name,level,components,spell_resistance,class_linked,url,description,creatures)
             VALUES(?,?,?,?,?,?,?,?) '''

insert_creature_query = ''' INSERT OR REPLACE INTO creature(name,url)
             VALUES(?,?) '''

drop_table_spell_query = '''DROP TABLE IF EXISTS spell'''
drop_table_creature_query = '''DROP TABLE IF EXISTS creature'''


class SqLite():
    def __init__(self):

        try:
            self.sqliteConnection = sqlite3.connect(db_name)
            self.create_base()

        except sqlite3.Error as error:
            print("can't open dataBase", error)
            self.sqliteConnection.close()

    def create_base(self):
        try:
            cursor = self.sqliteConnection.cursor()
            cursor.execute(create_spell_query)
            self.sqliteConnection.commit()
            cursor.close()
            print("spell table creation done")

            cursor = self.sqliteConnection.cursor()
            cursor.execute(create_creature_query)
            self.sqliteConnection.commit()
            cursor.close()
            print("creature table creation done")
        except sqlite3.Error as error:
            print("can't create SqliteTable", error)
            self.sqliteConnection.close()

    def put_spell(self, spell_class):

        try:
            self.sqliteConnection = sqlite3.connect(db_name)
            cursor = self.sqliteConnection.cursor()
            cursor.execute(insert_spell_query, (
                spell_class.name, spell_class.level, ':'.join(spell_class.components), spell_class.resistance, spell_class.classLinked,spell_class.url,spell_class.description, ':'.join(spell_class.creatures),))
            self.sqliteConnection.commit()
            cursor.close()
            print("Data put in spell sqlLite")
        except sqlite3.Error as error:
            print("can't put spell data", error)

    def put_creature(self, creature_class):
        try:
            self.sqliteConnection = sqlite3.connect(db_name)
            cursor = self.sqliteConnection.cursor()
            cursor.execute(insert_creature_query, (creature_class.name, creature_class.url))
            self.sqliteConnection.commit()
            cursor.close()
            print("Data put in creature sqlLite")
        except sqlite3.Error as error:
            print("can't put creature data", error)

    def drop_table(self):
        try:
            self.sqliteConnection = sqlite3.connect(db_name)
            cursor = self.sqliteConnection.cursor()
            cursor.execute(drop_table_spell_query)
            self.sqliteConnection.commit()
            cursor.close()
            print("table spell drop done")

            cursor = self.sqliteConnection.cursor()
            cursor.execute(drop_table_creature_query)
            self.sqliteConnection.commit()
            cursor.close()
            print("table creature drop done")
            self.create_base()

        except sqlite3.Error as error:
            print("can't drop table", error)
