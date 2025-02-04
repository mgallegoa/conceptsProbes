import React from "react";
import { todoTitle } from "../types";
import { CreateTodo } from "./CreateTodo";

interface Props {
  onAddTodo: ({ title }: todoTitle) => void;
}

export const Header: React.FC<Props> = ({ onAddTodo }) => {
  return (
    <header className="header">
      <h1>TypeScript</h1>
      <CreateTodo saveTodo={onAddTodo} />
    </header>
  );
};
