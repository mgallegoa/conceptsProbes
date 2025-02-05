import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import App from "../App";

describe("<App />", () => {
  test("End to end test", () => {
    render(<App />);
    // screen.debug();
    expect(screen.getByText("Add Element to a list app")).toBeDefined();
  });
});
