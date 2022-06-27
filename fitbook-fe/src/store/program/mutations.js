export default {
    setName(state, payload) {
        state.name = payload.name
    },
    setDescription(state, payload) {
        state.description = payload.description
    },
    setExcercises(state, payload) {
        state.excercises = payload
    }
}