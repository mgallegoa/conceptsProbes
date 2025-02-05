import { describe, test, expect } from "vitest";
import { renderHook, act } from "@testing-library/react";
import { useItems } from "../hooks/useItems";

describe("Test useItem hook", () => {
  test("Should add and remove items, integration test", () => {
    const { result } = renderHook(() => useItems());

    expect(result.current.items).toEqual([]);

    act(() => {
      result.current.addItem("Study React");
    });

    act(() => {
      result.current.addItem("Study Java");
    });

    expect(result.current.items.length).toBe(2);

    act(() => {
      result.current.removeItem(result.current.items[0].id);
    });
    expect(result.current.items.length).toBe(1);
  });
});
