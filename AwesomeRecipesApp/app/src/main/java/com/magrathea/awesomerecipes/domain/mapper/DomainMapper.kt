package com.magrathea.awesomerecipes.domain.mapper

interface DomainMapper <T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): T
    fun toDomainList(initial: List<T>): List<DomainModel>
    fun fromDomainList(initial: List<DomainModel>): List<T>
}