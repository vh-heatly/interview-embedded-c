import { useEffect, useState } from 'react'

interface LightBulb {
	id: string
	isOn: boolean
}

interface LightBulbsHook {
	lightBulbs: LightBulb[]
	toggleLightBulb: (id: string) => void
}

// Don't touch this, it's just a fake hook to simulate how you would get and set data from an API
export default function useLightBulbs(): LightBulbsHook {
	const [lightBulbs, setLightBulbs] = useState<LightBulb[]>([])

	const toggleLightBulb = (id: string) => {
		setLightBulbs((prevLightBulbs) =>
			prevLightBulbs.map((lightBulb) => {
				if (lightBulb.id === id) {
					return { ...lightBulb, isOn: !lightBulb.isOn }
				}
				return lightBulb
			})
		)
	}

	useEffect(() => {
		setTimeout(() => {
			setLightBulbs([
				{ id: 'e133c588-b7d2-45f4-ab45-59f363e108a9', isOn: true },
				{ id: '28cb5343-d964-4ea3-9bd4-d20d00ef980d', isOn: false },
				{ id: '57d1ab39-8bd3-4e84-8f03-955b0e8f6610', isOn: false },
				{ id: '57d1ab39-8bd3-4e84-8f03-955b0e8f6610', isOn: true },
			])
		}, 1000)
	}, [])

	return { lightBulbs, toggleLightBulb }
}
