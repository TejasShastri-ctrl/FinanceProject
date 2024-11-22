import { LOGIN, LOGOUT, REGISTER, ADD_EXPENSE, FETCH_EXPENSES, DELETE_EXPENSE } from './ActionType'; // Adjust the path as necessary

const initialState = {
    currUser: null,
    expenses: []
};

export const userReducer = (store = initialState, { type, payload }) => {
    switch (type) {
        case REGISTER:
            return { ...store, currUser: payload };
        case LOGIN:
            return { ...store, currUser: payload };
        case LOGOUT:
            return { ...store, currUser: null };
        case ADD_EXPENSE:
        // Do nothing
        case FETCH_EXPENSES:
            return { ...store, expenses: payload };
        case DELETE_EXPENSE:
            // return {
            //     ...store,
            //     expenses: store.expenses.filter(expense => expense.id !== payload) // Remove the deleted expense by id
            // };
        default:
            return store;
    }
};