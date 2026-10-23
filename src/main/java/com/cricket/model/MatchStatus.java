package com.cricket.model;

/**
 * Enum representing the current status of a cricket match.
 */
public enum MatchStatus {
    SCHEDULED,    // Match is scheduled but not started
    LIVE,         // Match is currently in progress
    INNINGS_BREAK,// Break between innings
    COMPLETED,    // Match has finished
    ABANDONED,    // Match was abandoned (weather/other reasons)
    CANCELLED     // Match was cancelled
}

