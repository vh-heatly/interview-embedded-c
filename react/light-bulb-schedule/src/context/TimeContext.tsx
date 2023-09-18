import { createContext, useContext, useEffect, useState } from 'react'

const TimeContext = createContext<number>(8)

export const useTime = () => {
	return useContext(TimeContext)
}

interface Props {
	children: React.ReactNode
}

export default function AuthProvider({ children }: Props) {
	const [time, setTime] = useState<number>(8)

	useEffect(() => {
		const incrementTime = () => {
			setTime((time) => (time === 23 ? 0 : time + 1))
		}

		// Increase time each second
		const interval = setInterval(incrementTime, 500)

		return () => clearInterval(interval)
	}, [])

	return <TimeContext.Provider value={time}>{children}</TimeContext.Provider>
}
