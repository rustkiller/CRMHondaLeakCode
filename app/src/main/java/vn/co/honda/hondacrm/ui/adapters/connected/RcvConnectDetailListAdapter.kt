package vn.co.honda.hondacrm.ui.adapters.connected;

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_connected_vehicle_detail.view.*
import vn.co.honda.hondacrm.R
import vn.co.honda.hondacrm.ui.fragments.connected.models.DetailInformationItem
import vn.co.honda.hondacrm.ui.fragments.connected.models.Vehicle

/**
 * Created by TienTM13 on 5/13/2019.
 */

class RcvConnectDetailListAdapter(
        private val item: Vehicle?,
        private val baseData: List<DetailInformationItem>?,
        private val context: Context,
        private val mRes: Resources
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return baseData!!.size
    }

    fun getItem(): Vehicle? {
        return item
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_connected_vehicle_detail, viewGroup, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).tvTitle.text = baseData?.get(position)?.name
        baseData?.get(position)?.img?.let { holder.ivImg.setImageResource(it) }

        when (baseData?.get(position)?.id) {
            0 -> holder.tvContent.text = item?.engineTemperature
            1 -> holder.tvContent.text = item?.distanceOfMaintenance
            2 -> holder.tvContent.text = item?.batteryVoltage
            3 -> holder.tvContent.text = item?.waterTemperature
            4 -> holder.tvContent.text = item?.intakeAirTemperature
            5 -> holder.tvContent.text = item?.gridOpeningDegree
            6 -> holder.tvContent.text = item?.throttleOpeningDegree
            7 -> holder.tvContent.text = item?.o2Sensor
            else -> return
        }
        holder.line.setOnClickListener {
            if (onItemClickedListener != null) {
                if (item != null) {
                    onItemClickedListener!!.onItemRcvDetailClick(position, item.name)
                } else {
                    onItemClickedListener!!.onItemRcvDetailClick(position, "Null")
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImg: ImageView = view.iv_item_connected_detail_img
        val tvTitle: TextView = view.tv_item_connected_detail_title
        val tvContent: TextView = view.tv_item_connected_detail_content
        val line: LinearLayout = view.line
    }

    // interface clicked for row
    interface OnItemClickedListener {
        fun onItemRcvDetailClick(pos: Int, name: String)
    }

    private var onItemClickedListener: OnItemClickedListener? = null
    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener
    }
}