package com.justinpauga.cyphersystemscharactertracker

import java.io.Serializable


class Character: Serializable {

    private var name: String = ""
    private var descriptor: String = ""
    private var type: String = ""
    private var focus: String = ""
    private var tier: Int = 1
    private var effort: Int = 0
    private var xp: Int = 0
    private var might: Int = 0
    private var speed: Int = 0
    private var intelligence: Int = 0
    private var abilities: String = ""
    private var attacks: String = ""
    private var cyphers: String = ""
    private var equipment: String = ""
    private var notes: String = ""


    fun setName(name: String) { this.name = name }
    fun getName(): String { return this.name }

    fun setDescriptor(descriptor: String) { this.descriptor = descriptor }
    fun getDescriptor(): String { return this.descriptor }

    fun setType(type: String) { this.type = type }
    fun getType(): String { return this.type }

    fun setFocus(focus: String) { this.focus = focus }
    fun getFocus(): String { return this.focus }

    fun setTier(tier: Int) { this.tier = tier }
    fun getTier(): Int { return this.tier }

    fun setEffort(effort: Int) { this.effort = effort }
    fun getEffort(): Int { return this.effort }

    fun setXp(xp: Int) { this.xp = xp }
    fun getXp(): Int { return this.xp }

    fun setMight(might: Int) { this.might = might }
    fun getMight(): Int { return this.might }

    fun setSpeed(speed: Int) { this.speed = speed }
    fun getSpeed(): Int { return this.speed }

    fun setIntelligence(intelligence: Int) { this.intelligence = intelligence }
    fun getIntelligence(): Int { return this.intelligence }

    fun setAbilities(abilities: String) { this.abilities = abilities }
    fun getAbilities(): String { return this.abilities }

    fun setAttacks(attacks: String) { this.attacks = attacks }
    fun getAttacks(): String { return this.attacks }

    fun setCyphers(cyphers: String) { this.cyphers = cyphers}
    fun getCyphers(): String {return this.cyphers }

    fun setEquipment(equipment: String) { this.equipment = equipment }
    fun getEquipment(): String { return this.equipment }

    fun setNotes(notes: String) { this.notes = notes }
    fun getNotes(): String { return this.notes }
}