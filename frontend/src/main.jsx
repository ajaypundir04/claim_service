import React from 'react'
import ReactDOM from 'react-dom/client'
import ClaimWizard from './components/ClaimWizard'
import './index.css'

const rootElement = document.getElementById('claim-widget-root');

if (rootElement) {
  const userId = rootElement.getAttribute('data-user-id');
  const apiUrl = rootElement.getAttribute('data-api-url') || 'http://localhost:8080';

  ReactDOM.createRoot(rootElement).render(
    <React.StrictMode>
      <ClaimWizard userId={userId} apiUrl={apiUrl} />
    </React.StrictMode>
  )
}