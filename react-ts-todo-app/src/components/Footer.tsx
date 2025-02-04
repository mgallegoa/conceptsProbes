import React from "react";
import { Filters } from "./Filters";
import { FilterValue } from "../types";

interface Props {
  activeCount: number;
  completedCount: number;
  filterSelected: FilterValue;
  handlerFilterChange: (filter: FilterValue) => void;
  onClearCompleted: () => void;
}

export const Footer: React.FC<Props> = ({
  activeCount = 0,
  completedCount = 0,
  filterSelected,
  handlerFilterChange,
  onClearCompleted,
}) => {
  return (
    <footer className="footer">
      <span className="todo-count">
        <strong>{activeCount}</strong> pending task
      </span>
      <Filters
        filterSelected={filterSelected}
        onFilterChange={handlerFilterChange}
      />
      {completedCount > 0 && (
        <button className="clear-completed" onClick={onClearCompleted}>
          Delete completed
        </button>
      )}
    </footer>
  );
};
