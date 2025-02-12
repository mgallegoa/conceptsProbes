/// <reference types='vitest' />

import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  test: {
    environment: "happy-dom",
  },
  server: {
    allowedHosts: [".direct.labs.play-with-docker.com", "localhost"],
    // host: "0.0.0.0", // Allow external access
    // port: 5174, // Ensure it's the correct port, the command in package.json do it.
    // strictPort: true, // Ensures Vite uses this exact port
  },
});
