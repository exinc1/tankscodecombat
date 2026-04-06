// Edge Case Test Bot 1 - Tests edge values
function run(state) {
    // Test ROTATE with extreme values
    return { type: "ROTATE", param: 720 };  // 720 degrees = 0 (2 full rotations)
}

function run2(state) {
    // Test ROTATE with negative values
    return { type: "ROTATE", param: -180 };  // Should be 180
}
