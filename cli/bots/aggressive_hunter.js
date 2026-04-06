// Test Bot 1 - Aggressive Hunter
// Tests: ROTATE, ROTATE_TURRET, FIRE, MOVE, RELOAD

function run(state) {
    // First turn: rotate body and turret toward enemy, then fire
    if (state.speed === 0) {
        return { type: "MOVE", param: 2 };  // Move fast toward enemy
    }
    
    // Aim turret at enemy
    if (state.turret !== state.radar) {
        return { type: "ROTATE_TURRET", param: state.radar };
    }
    
    // Fire!
    if (state.ammo === 1) {
        return { type: "FIRE", param: 0 };
    }
    
    // Reload
    return { type: "RELOAD", param: 1 };
}
