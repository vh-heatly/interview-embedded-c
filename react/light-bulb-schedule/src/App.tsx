import './App.css'
import LightBulbOff from './assets/light-bulb-off.png'
import LightBulbOn from './assets/light-bulb-on.png'
import { useTime } from './context/TimeContext'
import useLightBulbs from './hooks/useLightBulbs'

function App() {
	const { lightBulbs } = useLightBulbs()

	const time = useTime()

	return (
		<div>
			<h1>{time > 9 ? time : `0${time}`}:00</h1>
			{lightBulbs.map((lightBulb) => (
				<img height={100} src={lightBulb.isOn ? LightBulbOn : LightBulbOff} />
			))}
		</div>
	)
}

export default App
