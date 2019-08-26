package com.closeratio.aoc2018.day22

import com.closeratio.aoc2018.day22.RegionType.*

enum class EquipmentType {
    CLIMBING_GEAR,
    TORCH,
    NONE;

    fun canBeUsedIn(regionType: RegionType): Boolean = when (regionType) {
        ROCKY -> this == CLIMBING_GEAR || this == TORCH
        WET -> this == CLIMBING_GEAR || this == NONE
        NARROW -> this == TORCH || this == NONE
    }
}
