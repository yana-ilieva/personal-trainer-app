export default {
    setNameAndDesc(context, payload) {
        context.commit('setName', {name: payload.name})
        context.commit('setDescription', {description: payload.description})
    },
    setExercises(context, payload) {
        context.commit('setExercises', payload)
    }
}