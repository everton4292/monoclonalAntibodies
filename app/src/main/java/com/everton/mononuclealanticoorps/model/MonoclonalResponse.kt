package com.everton.mononuclealanticoorps.model

data class MonoclonalResponse(
    val results:List<Monoclonal>
)

data class Monoclonal (
    val _id: String,
    val monoclonalId: String,
    val name: String,
    val target: String,
    val concentration: String,
    val dose: String,
    val cycles: String,
    val risk: String,
    val infusionTime: String,
    val premedication: String,
    val filter: String,
    val photosensibility: String,
    val other: String
)
