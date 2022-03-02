const initialState = {
	userId: "",
	username: "",
	firstName: "",
	lastName: "",
	accounts: [],
};

function reducer(state = initialState, action) {
	switch (action.type) {
		case "UPDATE_ID":
			return {
				...state,
				userId: action.payload,
			};

		case "UPDATE_USERNAME":
			return {
				...state,
				username: action.payload,
			};

		case "UPDATE_NAME_FIRST":
			return {
				...state,
				firstName: action.payload,
			};

		case "UPDATE_NAME_LAST":
			return {
				...state,
				lastName: action.payload,
			};

		// Test this - Not sure if this is legal
		case "UPDATE_ACCOUNTS":
			return {
				...state,
				accounts: state.accounts.append(action.payload),
			};

		default:
			return state;
	}
}

export default reducer;
