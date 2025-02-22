import React from 'react';
import { render } from '@testing-library/react';
// import { test, expect } from '@jest/globals';
import App from './App';

test('renders learn react link', () => {
  const { getByText } = render(<App />);
  const functionComponent = getByText(/This title is for the function component/i);
  expect(functionComponent).toBeInTheDocument();
  const classComponent = getByText(/This title is for the Class component/i);
  expect(classComponent).toBeInTheDocument();
});
