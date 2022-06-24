export default {
    getName(state) {
        console.log('state name: ', state.name);
        return state.name
    },
    getDescription(state) {
        console.log('state description: ', state.description);
        return state.description
    },
}