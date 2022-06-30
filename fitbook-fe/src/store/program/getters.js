export default {
    getName(state) {
        return state.name
    },
    getDescription(state) {
        return state.description
    },
    getExercises(state) {
        return state.exercises
    },
    getProgram(state) {
        return {name: state.name, description: state.description, exercises: state.exercises}
    }
}