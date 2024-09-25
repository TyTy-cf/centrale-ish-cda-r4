package fr.cda.centaleish.service.interfaces;

public interface ServiceInterface<T, L, C, U> extends BasicServiceInterface<T, L, C> {

    T update(U o, L id);

    T findOneById(L id);

}
