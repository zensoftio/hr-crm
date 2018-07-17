import React from 'react';
import Header from './Header';
import './statistics.css'

const Statistics = () => {

	const statistics = [
		{
			desc: "Общее количество позиций",
			amount: 123
		},
		{
			desc: "Открытые Позиции",
			amount: 234
		},
		{
			desc: "Закрытые Позиции",
			amount: 456
		}
	];
  return (
    <div>
      <Header title="Статистика"/>
			<div className="statistic">
				<ul>
					{statistics.map((stat, i) => {
						return (
							<li key={i}>
								<span className="desc">{stat.desc} </span>
								<span className="amount">{stat.amount}</span>
							</li>
						)
					})}
				</ul>
			</div>
    </div>
  )
}

export default Statistics;