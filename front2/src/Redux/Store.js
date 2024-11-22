import { createStore, applyMiddleware, combineReducers } from 'redux';
import { thunk } from 'redux-thunk';

import { userReducer } from './Reducer';
import { expenseReducer } from './ExpenseReducer';

const reducer = combineReducers({
    user: userReducer
    // expense: expenseReducer
});

const store = createStore(reducer, applyMiddleware(thunk));

export default store;
