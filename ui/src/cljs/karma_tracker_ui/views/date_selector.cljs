(ns karma-tracker-ui.views.date-selector
  (:require [re-frame.core :as re-frame]
            [karma-tracker-ui.routes :as routes]
            [karma-tracker-ui.views.format :as format]
            [karma-tracker-ui.views.svg :as svg]))

(defn current-date []
  (let [date (re-frame/subscribe [:current-date])]
    (fn []
      [:h1.date-selector__current (format/full-month-and-year @date)])))

(defn previous-date []
  (let [previous-date (re-frame/subscribe [:previous-date])]
    (fn []
      [:a.date-selector__nav.date-selector__nav--previous {:href (routes/overview @previous-date)}
       [svg/icon "previous" "date-selector__nav-icon"]
       [:span.date-selector__nav-text (format/short-month @previous-date)]])))

(defn next-date []
  (let [next-date (re-frame/subscribe [:next-date])]
    (fn []
      [:a.date-selector__nav.date-selector__nav--next {:href (routes/overview @next-date)}
       [:span.date-selector__nav-text (format/short-month @next-date)]
       [svg/icon "next" "date-selector__nav-icon"]])))

(defn date-selector []
  (fn []
    [:header.date-selector
     [:div.date-selector__container
      [current-date]
      [previous-date]
      [next-date]]]))
