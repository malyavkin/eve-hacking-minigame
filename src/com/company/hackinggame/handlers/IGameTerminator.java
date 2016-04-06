package com.company.hackinggame.handlers;

/**
 * Created by lich on 2016-04-06.
 */
public interface IGameTerminator {
    void onContainerOpen(); // you win
    void onVirusDestruction(); // you lost
}
