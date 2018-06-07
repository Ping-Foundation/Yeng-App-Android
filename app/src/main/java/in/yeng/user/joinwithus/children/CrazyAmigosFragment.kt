package `in`.yeng.user.joinwithus.children

import `in`.yeng.user.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class CrazyAmigosFragment : Fragment() {

    private var _context: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.join_crazy_amigos_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<ImageView>(R.id.email_icon)
        val call = view.findViewById<ImageView>(R.id.call_icon)
        val joinTelegram = view.findViewById<View>(R.id.join_telegram)

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", resources.getString(R.string.crazy_amigos_contact_email), null))
            startActivity(Intent.createChooser(intent, "Send email..."))
        }

        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", resources.getString(R.string.crazy_amigos_contact_call), null))
            startActivity(intent)
        }

        joinTelegram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/crazyamigos"))
            startActivity(intent)
        }
    }
}