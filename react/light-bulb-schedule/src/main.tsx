import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import TimeProvider from './context/TimeContext.tsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')!).render(
	<React.StrictMode>
		<TimeProvider>
			<App />
		</TimeProvider>
	</React.StrictMode>
)
