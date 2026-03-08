import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';

export default defineConfig({
  plugins: [react()],
  build: {
    rollupOptions: {
      input: resolve(__dirname, 'index.html'), // Explicitly point to the html
      output: {
        manualChunks: false,
        inlineDynamicImports: true,
        entryFileNames: 'claim-widget.js',
        assetFileNames: 'claim-widget.[ext]',
      },
    },
  },
});