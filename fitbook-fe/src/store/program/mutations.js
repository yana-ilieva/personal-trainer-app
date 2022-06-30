export default {
    setName(state, payload) {
        state.name = payload.name
    },
    setDescription(state, payload) {
        state.description = payload.description
    },
    setExercises(state, payload) {
        state.exercises = payload
    }
}