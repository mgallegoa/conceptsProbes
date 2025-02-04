import React, { useState } from "react";
import { todoTitle } from "../types";

interface Props {
  saveTodo: ({ title }: todoTitle) => void;
}
export const CreateTodo: React.FC<Props> = ({ saveTodo }) => {
  const [inputValue, setInputValue] = useState("");

  // const handleKeyDown = (
  //   event: React.KeyboardEvent<HTMLInputElement>,
  // ): void => {};

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    saveTodo({ title: inputValue });
    setInputValue("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        className="new-todo"
        value={inputValue}
        type="text"
        placeholder="New Task"
        onChange={(event) => {
          setInputValue(event.target.value);
        }}
        autoFocus
      />
    </form>
  );
};
