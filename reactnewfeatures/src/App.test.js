import React from 'react';
import { render } from '@testing-library/react';
// import { test, expect } from '@jest/globals';
import App from './App';
import {UseOfProps, ClassComponent} from './App.js'

test('renders the app with function and class component', () => {
  const { getByText } = render(<App />);
  const functionComponent = getByText(/This title is for the function component/i);
  expect(functionComponent).toBeInTheDocument();
  const classComponent = getByText(/This title is for the Class component/i);
  expect(classComponent).toBeInTheDocument();
});

it('Correct render the functional component', () => {
  const { getByText } = render(<UseOfProps title='Mock Title Functional Component' text='Text'/>);
  const functionComponent = getByText(/Mock Title Functional Component/i);
  expect(functionComponent).toBeInTheDocument();
})

it('Correct render the class component', () => {
  const { getByText } = render(<ClassComponent title='Mock Title Class Component' text='Text'/>);
  const functionComponent = getByText(/Mock Title Class Component/i);
  expect(functionComponent).toBeInTheDocument();
})
