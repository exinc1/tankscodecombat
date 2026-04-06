// Test Bot 2 - Defensive Shooter
// Tests: All action types with edge cases

function run(state) {
    // Turn 1: Rotate body (toward enemy if possible)
    if (state.direction !== state.radar) {
        return { type: "ROTATE", param: state.radar };
    }
    
    // Turn 2: Move slowly
    return { type: "MOVE", param: 1 };
    
    // After that: fire when aimed, reload when empty
    // Fire bullet
    return { type: "FIRE", param: 0 };
}
