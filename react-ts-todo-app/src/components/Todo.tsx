import React from "react";
import { todoId, type Todo as TodoType } from "../types";

interface Props extends TodoType {
  onRemoveTodo: ({ id }: todoId) => void;
  onCompleted: ({ id, completed }: Pick<TodoType, "id" | "completed">) => void;
}

export const Todo: React.FC<Props> = ({
  title,
  id,
  completed,
  onRemoveTodo,
  onCompleted,
}) => {
  const handleChangeCheckbox = (
    event: React.ChangeEvent<HTMLInputElement>,
  ): void => {
    onCompleted({
      id,
      completed: event.target.checked,
    });
  };

  return (
    <div className="view" key={id}>
      <input
        className="toggle"
        type="checkbox"
        checked={completed}
        onChange={handleChangeCheckbox}
      />
      <label>{title}</label>
      <button
        className="destroy"
        onClick={() => {
          onRemoveTodo({ id });
        }}
      />
    </div>
  );
};
