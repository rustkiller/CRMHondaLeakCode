package vn.co.honda.hondacrm.ui.adapters.connected

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import vn.co.honda.hondacrm.R
import vn.co.honda.hondacrm.ui.customview.ExpandableTextView
import vn.co.honda.hondacrm.ui.fragments.connected.models.DetailInformationItem
import vn.co.honda.hondacrm.ui.fragments.connected.models.Vehicle

class RcvConnectedDetailLinearLayout(
        private val baseData: ArrayList<DetailInformationItem>,
        private val context: Context?,
        private val item: Vehicle?
) :
        RecyclerView.Adapter<RcvConnectedDetailLinearLayout.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_connected_detail_llc, p0, false))
    }

    override fun getItemCount(): Int {
        return baseData.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.posItem = position
        holder.tvTitle.text = baseData[position].name
        holder.tvDetail.text = baseData[position].detail
        baseData[position].img?.let { holder.imInfo.setImageResource(it) }

        when (baseData[position].id) {
            0 -> holder.tvData.text = item?.engineTemperature
            1 -> holder.tvData.text = item?.distanceOfMaintenance
            2 -> holder.tvData.text = item?.batteryVoltage
            3 -> holder.tvData.text = item?.waterTemperature
            4 -> holder.tvData.text = item?.intakeAirTemperature
            5 -> holder.tvData.text = item?.gridOpeningDegree
            6 -> holder.tvData.text = item?.throttleOpeningDegree
            7 -> holder.tvData.text = item?.o2Sensor
            else -> return
        }




        holder.itemView.setOnClickListener {
            //            if (clickListener != null) {
//                clickListener?.onItemClick(position, item!!.name)
//                pos = position
//                notifyDataSetChanged()
            holder.tvDetail.setOnClick()
            if (holder.tvDetail.getTrim()) {
                holder.rlDetail.visibility = View.GONE
                changeState(false, holder.tvData, holder.imInfo, holder.tvTitle, holder.llInfo, holder.pbInfo)
            } else {
                val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
                holder.rlDetail.visibility = View.VISIBLE
                holder.rlDetail.startAnimation(slideDown)
                changeState(true, holder.tvData, holder.imInfo, holder.tvTitle, holder.llInfo, holder.pbInfo)
            }

//            }
        }
    }

    private fun changeState(
            state: Boolean,
            tvData: TextView,
            imgInfo: ImageView,
            tvTitle: TextView,
            llInfo: LinearLayout,
            pbInfo: ProgressBar
    ) {
        val res: Resources = context!!.resources
        if (state) {
            tvData.setTextColor(Color.WHITE)
            tvTitle.setTextColor(Color.WHITE)
            imgInfo.setColorFilter(Color.WHITE)
            llInfo.setBackgroundColor(ResourcesCompat.getColor(res, R.color.red, null))

            pbInfo.progressDrawable = ResourcesCompat.getDrawable(res, R.drawable.custom_progressbar_state, null)
        } else {
            tvData.setTextColor(Color.BLACK)
            tvTitle.setTextColor(Color.BLACK)
            imgInfo.setColorFilter(Color.GRAY)
            llInfo.setBackgroundColor(ResourcesCompat.getColor(res, R.color.white, null))
            pbInfo.progressDrawable = ResourcesCompat.getDrawable(res, R.drawable.custom_progressbar_gray, null)
        }


    }

    private var clickListener: ClickListener? = null
    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(p: Int, name: String)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var posItem: Int? = null
        var imInfo: ImageView = view.findViewById(R.id.iv_info)
        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var tvData: TextView = view.findViewById(R.id.tv_data)
        var pbInfo: ProgressBar = view.findViewById(R.id.pbInfo)
        var tvDetail: ExpandableTextView = view.findViewById(R.id.tv_detail)
        var llInfo: LinearLayout = view.findViewById(R.id.ll_info)
        var rlDetail: RelativeLayout = view.findViewById(R.id.rl_detail)
    }

}