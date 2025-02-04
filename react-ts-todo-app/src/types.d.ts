import { TODO_FILTERS } from "./const";

export interface Todo {
  id: string;
  title: string;
  completed: boolean;
}

export type todoId = Pick<Todo, "id">;
export type todoTitle = Pick<Todo, "title">;
export type todoCompleted = Pick<Todo, "completed">;

export type ListOfTodos = Todo[];

export type FilterValue = (typeof TODO_FILTERS)[keyof typeof TODO_FILTERS];
